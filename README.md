# kennyfy-api

Simple API which translates given text to kennyspeak.

Done for learning purpose.

## Installation

```
lein uberjar
```

## Usage

```
java -jar kennyfy-api-0.1.0.jar
```

or, using `lein`:

```
lein run
```

or, using `docker`:

```
docker run --rm -p 8080:8080 radmen/kennyfy-api
```

## Options

* `-p | --port` port number (default: `8080`)
* `-d` development mode - will reload handlers on changes

## API endpoints

`POST /speak` - converts text to kennyspeak

```
$ curl -X post http://localhost:8080/speak -H "Content-type: application/json" -d '{"text": "Foo"}'
{"text":"Mpfppfppf"}
```

`POST /translate` - decodes kennyspeak

```
$ curl -X post http://localhost:8080/translate -H "Content-type: application/json" -d '{"text": "Mpfppfppf"}'
{"text":"Foo"}
```

## License

Copyright © 2019 Radoslaw Mejer

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
