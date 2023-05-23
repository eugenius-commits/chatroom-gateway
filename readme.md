# Chatroom Gateway Spring Boot Application

This is a sample Spring Boot application that serves as a gateway for microservices using Netflix Zuul, Feign Client, and OAuth2 security.

## Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK) 11 or higher
- Apache Maven
- Docker (optional, for running any required supporting services)

## Getting Started

1. Clone the repository:

   ```shell
   git clone <repository-url>
   ```

2. Build the application using Maven:

   ```shell
   cd gateway-chatroom
   mvn clean install
   ```

3. Start the application:

   ```shell
   java -jar target/gateway-chatroom.jar
   ```

4. The application will be available at `http://localhost:8080`.

## Configuration

The application can be configured through the following properties in `application.yml`:

```yaml
# Zuul Configuration
zuul:
  routes:
    service1:
      path: /service1/**
      url: http://localhost:8001
    service2:
      path: /service2/**
      url: http://localhost:8002

# Feign Configuration
feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000

# OAuth2 Configuration
security:
  oauth2:
    client:
      clientId: your-client-id
      clientSecret: your-client-secret
      accessTokenUri: https://example.com/oauth2/token
    resource:
      userInfoUri: https://example.com/oauth2/userinfo
```

Make sure to replace the placeholder values with your actual configuration details.

## Usage

The gateway application acts as a proxy for the registered microservices. To access a microservice through the gateway, use the following pattern:

```
http://localhost:8080/{microservice-context-path}/{microservice-endpoint}
```

For example, to access `service1` endpoint `example`:

```
GET http://localhost:8080/service1/example
```

## Documentation

For more information on how to use Zuul, Feign Client, and OAuth2 security, refer to the following documentation:

- [Netflix Zuul Documentation](https://github.com/Netflix/zuul/wiki)
- [Feign Client Documentation](https://github.com/OpenFeign/feign)
- [Spring Security OAuth2 Documentation](https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html5/)

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to modify and enhance the application according to your requirements.

Please note that this is a general template, and you may need to adapt it to fit your specific application's architecture and requirements.