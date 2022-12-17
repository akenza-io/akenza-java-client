# Contribution Guidelines

- **To report a bug; add, remove, or change something in the akenza-java-client** Please submit an issue first.
- Submit a pull request to close an issue.

## Code owners

To make sure every PR is checked, we have [code owners](.github/CODEOWNERS). Every PR MUST be reviewed by at least one
code owner before it can get merged.

The code owners will review your PR and notify you and tag it in case any information is still missing. They will wait
14 days for your interaction, after that the PR will be closed.

## Reporting issues

Please open an issue if you would like to discuss anything that could be improved or have suggestions.

Thanks!

## Development

### Build and Test

```
mvn clean install
```

### Deploying a Snapshot Version

Make sure that `~/.m2/settings.xml` exists and that you have the appropriate access to publish to the snapshot
repository.

```
mvn clean deploy -P release
```

## Releasing

Can only be performed by CODEOWNERS

```
export VERSION=0.0.3

git checkout main
git pull
mvn versions:set -DnewVersion=$VERSION

git add pom.xml
git commit -m "updated version in pom.xml"
git push

git tag $VERSION
git push origin --tags
gh release create $VERSION
```

## Troubleshooting

- Maven Central Password contains special characters: make sure to properly XML-escape all special chars that will end
  up in Maven settings.xml