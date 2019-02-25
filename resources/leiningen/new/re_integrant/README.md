# {{name}}

[re-integrant](https://github.com/re-integrant/core) application.

## Setup

To get an interactive development environment run:

```sh
lein repl
```

Then start figwheel:

```clojure
user=> (fig-start)
```

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload.

After the compilation process is complete, you will
get a Browser Connected REPL:

```clojure
user=> (cljs-repl)
```

```clojure
user=> (js/alert "Am I connected?")
```

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2019 FIXME

Distributed under the MIT license.