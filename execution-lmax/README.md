# quickfix-lmax-client
Quickfix/j lmax client used to subscribe to crypto market-data and order execution.

# Getting Started

## quickfixj-client.cfg

You have to configure your own quickfixj-client.cfg using the file on:
  
    scripts/quickfixj-client.cfg.base 

it is necessary to add:
* SenderCompID
* TargetCompID

## Quickfix Client configuration

    http://localhost:8090/actuator/quickfixjclient

## Subscribe to market-data

Subscription End-Point

    POST http://localhost:8091/api/v1/order/new

* Post Event:
    
    Order request:
    
    String refId,
    String ticker,
    Side side,
    LocalDateTime requestTimestamp,
    OrderType orderType,
    Double amount,
    String orderId,
    LocalDateTime orderTimestamp,
    Double limitPrice

* Currencies available
    - XBTUSD
    - XETUSD
  
### Spring Boot Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

