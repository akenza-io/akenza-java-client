# akenza-java-client

![release](https://github.com/akenza-io/akenza-java-client/actions/workflows/release.yml/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![lifecycle: alpha](https://img.shields.io/badge/lifecycle-alpha-cf3d26.svg)
![Maven Central](https://img.shields.io/maven-central/v/io.akenza/akenza-java-client)

A Java client library for interacting with the `akenza` API.

**Disclaimer: this project is under active development and the APIs are not yet stable**

## Getting Started

Include the latest version of akenza-client into your project:

In Maven:

```xml

<dependency>
    <groupId>io.akenza</groupId>
    <artifactId>akenza-java-client</artifactId>
    <version>0.0.3</version>
</dependency>
```

Start using the akenza API.

```java
final String apiKey="";
final AkenzaAPI client=AkenzaAPI.create(apiKey);
final Workspace workspace=client.workspaces().getById("<workspaceId>").execute();
```

Refer to [akenza API Keys](https://docs.akenza.io/api-reference/api-documentation#api-keys) for more information.

## Usage

Refer to the [`./src/test`](./src/test) directory to see examples on how to use the library.

## Contributing

This project uses Maven. To run the tests locally, just run:

```bash
mvn clean verify
```

For more infos about contributing refer to [CONTRIBUTING.md](./CONTRIBUTING.md)
Inspired by the [Spotify Github Java Client](https://github.com/spotify/github-java-client/)
and [Auth0 Java Client](https://github.com/auth0/auth0-java).

## Open Points

- device types do not return the proper version, updated field, should use include non null for some of the fields
- return organization version
- use Instants instead of strings for dates
- conflicting models for asset and device
- add bulk operations
- add register/deregister actions
- check rate-limiting
- check exception handling
- async client
- add instrumentation
- deprecate the use of custom payload on properties level for output connectors
- check downlink properties (custom payload vs raw payload)
- use JSON include non null for output connectors and on other places
- data flows do not return created updated version
- isPassThrough is somehow serialized as passThrough for data flows
- provide an easy way of mapping entity to update command
- add support for bearer token authentication
- add rule validations in the library
- the list device configuration endpoint should not require the workspaceId
- custom logic blocks do not have created and updated
- add shortcuts for the list calls with default pagination
- change filters to builder pattern instead of .create()
- add examples on how to use the library
- find out if shading works as it should
- add more logs