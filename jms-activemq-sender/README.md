# JMS ActiveMQ Sender

## Requirements

Apache ActiveMQ (make sure it is running!)

### Installing ActiveMQ in macOS

Using Brew:

```sh
brew install activemq
```

Then run the service:

```sh
activemq start
```

Go to the admin page: http://localhost:8161/admin/

## Running

```sh
mvn package && java -jar target/jms-activemq-sender-0.1.0.jar
```

## Usage

Type `1` if you would like to send a message. Any other number will terminate the program.