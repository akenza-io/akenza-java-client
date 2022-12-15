# akenza-java-client

A Java client library for interacting with the `akenza` API.

## Getting Started

Include the latest version of akenza-client into your project:

In Maven:

```xml

<dependency>
    <groupId>io.akenza.client</groupId>
    <artifactId>akenza-java-client</artifactId>
    <version>0.0.1</version>
</dependency>
```

Start using the akenza API.

```java
final String apiKey="";
final AkenzaAPI client=AkenzaAPI.create(apiKey);
final Workspace workspace=akenza.workspaces().getById("<workspaceId>").execute();
```

Refer to [akenza API Keys](https://docs.akenza.io/api-reference/api-documentation#api-keys) for more information.

## Usage

TODO

## Contributing

This project uses Maven. To run the tests locally, just run:

```bash
mvn clean verify
```

Inspired by the [Spotify Github Java Client](https://github.com/spotify/github-java-client/)
and [Auth0 Java Client](https://github.com/auth0/auth0-java).

## TODO

- device types do not return the proper version, updated field, should use include non null for some of the fields
- return organization version
- use Instants instead of strings for dates
- conflicting models for asset and device
- bulk operations
- register/deregister
- check rate-limiting
- check exception handling
- async client
- add instrumentation
- add special http header with library version
- deprecate the use of custom payload on properties level
- check downlink properties (custom payload vs raw payload)