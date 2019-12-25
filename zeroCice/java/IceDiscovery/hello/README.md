This demo is the Ice hello world demo configured to use [IceDiscovery][1].

To run the demo, first start the server:

```
java -jar build/libs/server.jar
```

In a separate window, start the client:

```
java -jar build/libs/client.jar
```

The client and server use IceDiscovery to enable the location of the
Ice hello object. See the `config.server` and `config.client` files for
details on the IceDiscovery configuration.

[1]: https://doc.zeroc.com/ice/3.7/ice-plugins/icediscovery
