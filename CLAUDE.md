# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Java client library (JDK 11 source/target, requires JDK 18 to build per CI) for the [akenza](https://akenza.io/) IoT
platform REST API. Published to Maven Central as `io.akenza:akenza-java-client`. The project is under active
development — APIs are not yet guaranteed stable (see "Open Points" in README.md for known gaps).

## Commands

Build and run the full test suite (unit + integration via failsafe):

```bash
mvn clean verify
```

Run unit tests only:

```bash
mvn test
# or
make test
```

Run a single test class or method:

```bash
mvn test -Dtest=WorkspaceClientTest
mvn test -Dtest=WorkspaceClientTest#getById
```

Full install (same as CI's `mvn verify -B`):

```bash
mvn clean install
```

Deploy a snapshot (requires `~/.m2/settings.xml` with OSSRH credentials):

```bash
mvn clean deploy -P release
```

Release (CODEOWNERS only, see CONTRIBUTING.md for the full tag/push sequence):

```bash
mvn versions:set -DnewVersion=$VERSION
```

## Architecture

### Resource clients under `v3/domain/*`

Every API resource (workspaces, devices, rules, tags, etc.) lives in its own package under
`src/main/java/io/akenza/client/v3/domain/<resource>/` following a consistent layout:

- `<Resource>Client.java` — extends `utils.BaseClient`, builds `okhttp3.HttpUrl` requests and returns
  `http.Request<T>` (never executes them itself — see below).
- `<Resource>.java` / `<Resource>Page.java` — response DTOs.
- `objects/` — nested value objects referenced by the resource or its commands.
- `commands/` — `Create<Resource>Command` / `Update<Resource>Command` request payloads.
- `queries/` — `<Resource>Filter` extending `utils.BaseFilter<T>` for list/pagination query params.

`v3/AkenzaAPI.java` is the single entry point: it owns the shared `OkHttpClient` (interceptors, timeouts, proxy,
dispatcher) and exposes one factory method per resource (`workspaces()`, `devices()`, `rules()`, ...), each
constructing a fresh `<Resource>Client` with the shared client/options. When adding a new resource, wire its client
factory method here.

### Request execution model

Client methods **build and return** a `Request<T>` (`RequestImpl<T>`) rather than executing it — callers explicitly
call `.execute()` (sync) or `.executeAsync()` (async, returns `CompletableFuture<T>`; not all requests support this).
`BaseClient.addAuthentication()` attaches either the `x-api-key` header or a `Bearer` token depending on
`AuthOptions`. JSON (de)serialization goes through `http/Json.java` (Jackson, with jdk8/jsr310 modules registered).
Non-2xx responses raise `exceptions.HttpClientResponseException` (parsed error body) or
`exceptions.RateLimitException` for HTTP 429 (reads `X-RateLimit-*` headers); `RateLimitInterceptor` handles retries
at the OkHttp layer.

### Immutables-based models

All domain models and commands are interfaces annotated `@Value.Immutable` + `@AkenzaStyle` (see
`utils/AkenzaStyle.java`: public generated visibility, JDK-only collections) and
`@JsonSerialize(as = Immutable<Type>.class)` / `@JsonDeserialize(as = Immutable<Type>.class)`. The annotation
processor generates the `Immutable<Type>` builder class used both in production code and tests — these generated
classes are not checked in, so an IDE/build must run annotation processing before symbols like
`ImmutableWorkspace` or `ImmutableCreateWorkspaceCommand` resolve. Common cross-cutting interfaces:
`utils.Audited` (created/updated timestamps) and `utils.Versioned` (optimistic locking version field), both commonly
extended by resource DTOs.

### Testing

Tests use JUnit 5 + AssertJ + Mockito, with `okhttp3.mockwebserver.MockWebServer` to stand up a fake HTTP server per
test class (see `WorkspaceClientTest` as the canonical example: enqueue a `MockResponse` with a JSON fixture, call
the client method, assert on the parsed model, and optionally inspect `server.takeRequest()` for path/body
assertions). JSON fixtures live under `src/test/resources/io/akenza/client/v3/domain/<resource>/*.json` and are
loaded via `TestUtils.getFixture("<resource>/<file>.json")`. Mirror this pattern (`*ClientTest` for HTTP behavior,
plain `*Test` for model/serialization behavior) for new resources. `maven-failsafe-plugin` and `jacoco` are wired
into the build for integration tests and coverage (uploaded to Codecov in CI).

## Contribution notes

- Every PR requires review from a code owner (`.github/CODEOWNERS`) before merging.
- Releases and snapshot deploys are restricted to CODEOWNERS.
