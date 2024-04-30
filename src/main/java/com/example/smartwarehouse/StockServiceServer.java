// StockServiceServer.java
package com.example.smartwarehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class StockServiceServer {
    private Server server;

    // Method to start the gRPC server
    private void start() throws IOException {
        /* The server will listen on port 50051 for incoming connections */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new StockServiceImpl()) // Register the StockService implementation
                .build()
                .start();
        System.out.println("Server started - listening on " + port);
        /* Register a shutdown hook to gracefully stop the server */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            StockServiceServer.this.stop();
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
        final StockServiceServer server = new StockServiceServer();
        server.start();
        server.blockUntilShutdown();
    }
}
