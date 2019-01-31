FROM openjdk:8-alpine as build
WORKDIR /usr/src/app

RUN apk add --no-cache bash

RUN wget -O /usr/bin/lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein \
	&& chmod +x /usr/bin/lein

COPY . /usr/src/app/

RUN lein uberjar \
	&& mv target/uberjar/kennyfy-api-*-standalone.jar target/uberjar/kennyfy-api.jar

FROM openjdk:8-alpine
WORKDIR /usr/src/app

ARG DOCKER_UID=1000
ARG DOCKER_GID=1000

ENV APP_PORT 8080

RUN addgroup -g $DOCKER_GID app \
	&& adduser -h /usr/src/app -u $DOCKER_UID -G app -D app

RUN apk add --no-cache libcap \
	&& setcap 'cap_net_bind_service=+ep' $(realpath $(which java))

# @see https://github.com/docker-library/openjdk/issues/77#issuecomment-302871030
COPY docker/ld-musl-x86_64.path /etc/ld-musl-x86_64.path

USER app

COPY --from=build --chown=app /usr/src/app/target/uberjar/kennyfy-api.jar /usr/src/app/

CMD ["java", "-jar", "kennyfy-api.jar", "--port", "$APP_PORT"]
