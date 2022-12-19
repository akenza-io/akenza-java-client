# akenza-java-client

![build](https://github.com/akenza-io/akenza-java-client/actions/workflows/pullrequest.yml/badge.svg?query=branch%3Amain)
[![codecov](https://codecov.io/github/akenza-io/akenza-java-client/branch/main/graph/badge.svg?token=MHT923B1BC)](https://codecov.io/github/akenza-io/akenza-java-client)
[![CodeQL](https://github.com/akenza-io/akenza-java-client/workflows/CodeQL/badge.svg)](https://github.com/akenza-io/akenza-java-client/actions?query=workflow%3ACodeQL "Code quality workflow status")
[![GitHub tag](https://img.shields.io/github/tag/akenza-io/akenza-java-client?include_prereleases=&sort=semver&color=blue)](https://github.com/akenza-io/akenza-java-client/releases/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![lifecycle: alpha](https://img.shields.io/badge/lifecycle-alpha-cf3d26.svg)
[![Maven Central](https://img.shields.io/maven-central/v/io.akenza/akenza-java-client)](https://search.maven.org/artifact/io.akenza/akenza-java-client)

A Java client library for interacting with the [`akenza`](https://akenza.io/) API.

**Disclaimer: this project is under active development and APIs are not yet guaranteed to be stable.**

## Getting Started

Include the latest version of `akenza-java-client` into your project:

Maven:

```xml

<dependency>
    <groupId>io.akenza</groupId>
    <artifactId>akenza-java-client</artifactId>
    <version>0.1.0</version>
</dependency>
```

Start using the akenza API:

```java
final String apiKey="";
final AkenzaAPI client=AkenzaAPI.create(apiKey);
final Workspace workspace=client.workspaces().getById("<workspaceId>").execute();
```

To create an api key, [sign up](https://auth.akenza.io/register) to  akenza and refer to [akenza API Keys](https://docs.akenza.io/api-reference/api-documentation#api-keys) to get started.

Refer to the [akenza documentation](https://docs.akenza.io/) and the [akenza API docs](https://docs.api.akenza.io/) for further information.

## Usage

Refer to the [`./src/test`](./src/test) directory for examples on how to use this library.

## Contributing

This project uses Maven. To run the tests locally, run:

```bash
mvn clean verify
```

For more infos about contributing refer to [CONTRIBUTING.md](./CONTRIBUTING.md)


## License

Released under [MIT](/LICENSE) by [@akenza-io](https://github.com/akenza-io).

## Open Points

- use Instants instead of strings for dates (Audited)
- conflicting models for asset and device
- add bulk operations
- add register/deregister actions
- check rate-limiting
- check exception handling
- add an async client
- add instrumentation
- provide an easy way of mapping entity to update command
- add support for bearer token authentication
- add rule validations in the library
- change filters to builder pattern instead of .create()
- add examples on how to use the library
- find out if okhttp shading works as it should, otherwise remove it
- add more logs
- add shortcuts for the list calls with default pagination

This library is inspired by [Spotify Github Java Client](https://github.com/spotify/github-java-client/)
and [Auth0 Java Client](https://github.com/auth0/auth0-java).