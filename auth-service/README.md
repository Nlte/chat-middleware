(1) configure GOPATH :
dir where the .go sources are
```
$ export GOPATH=$HOME/path/to/auth-service
```

(2) configure GOBIN :
where to compile the sources
```
$ export GOBIN=$GOPATH/bin
```
(3) compile
create binary from file.go to GOBIN directory
```
$ go install main.go
```
(4) Launch the authentication service
```
$ cd src/auth-service/bin
$ ./main
```
NOTE : The config file 'users.json' must be in the directory where we launch the service from.

To test the auth service :
```
$ curl -v -X POST --data-binary @auth-test.json http://127.0.0.1:8666/auth
# --> {"success":true}
```
