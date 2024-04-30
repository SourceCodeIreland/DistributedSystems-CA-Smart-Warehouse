// OrderServiceServer.java
package com.example.smartwarehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class OrderServiceServer {
    private Server server;

    // Method to start the gRPC server
    private void start() throws IOException {
        /* The server will listen on port 50052 for incoming connections */
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(new OrderServiceImpl()) // Register the OrderService implementation
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        /* Register a shutdown hook to  stop the server */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            OrderServiceServer.this.stop();
            System.err.println("*** Server shut down");
        }));
    }

    // Method to stop the gRPC server
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // Await termination on the main thread since the grpc library uses daemon threads
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // Main method to start the server
    public static void main(String[] args) throws IOException, InterruptedException {
        final OrderServiceServer server = new OrderServiceServer();
        server.start();
        server.blockUntilShutdown();
    }
}
