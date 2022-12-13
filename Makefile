ifeq (${VERSION},)
  VERSION=$(shell git describe --tags 2>/dev/null)
endif
ifeq (${GIT_COMMIT},)
  GIT_COMMIT=$(shell git log -1 --format='%H')
endif
ifeq (${GIT_BRANCH},)
  GIT_BRANCH=$(shell git rev-parse --abbrev-ref HEAD)
endif
ifeq (${JAR_FILE},)
  JAR_FILE=target/*.jar
endif

.PHONY: all

all: build

info: ## Show info
	@echo "  Version: ${VERSION}"
	@echo "  GitHash: ${GIT_COMMIT}"
	@echo "  Branch: ${GIT_BRANCH}"
	@echo "  App: ${JAR_FILE}"

install:
    mvn clean verify

test: ## Runs the tests
	mvn test

help: ## Display this help screen
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
