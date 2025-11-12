package com.server;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static PresentationTools presentationTools = new PresentationTools();


    public static void main(String[] args) {
        System.out.println("MCP Server is up and running  ..... ");
//        Stdio Server Transport
        var transportProvider = new StdioServerTransportProvider(new ObjectMapper());

//        Sync Tool Specifications
        var syncToolSpecification = getSyncToolSpecification();

        McpSyncServer mcpServer = McpServer.sync(transportProvider)
                .serverInfo("my-mcp-server","0.0.1")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .tools(true)
                        .logging()
                        .build()).build();

        log.info("MCP Server is up and running  ..... ");
    }

    private static McpServerFeatures.SyncToolSpecification getSyncToolSpecification() {
        var schema = """
                {
                    "type" : "object",
                    "id" : "urn:jsonschema:Operation",
                    "properties": {
                        "operation" : {
                            "type" : "string"
                        }
                    }
                }
                """;
        McpServerFeatures.SyncToolSpecification syncToolSpecification = new McpServerFeatures.SyncToolSpecification(
                new McpSchema.Tool("get+presentation" , "Get a list of all presentation from Java", schema),
                (McpSyncServerExchange exchange, Map<String,Object> arguments) -> {
//                     Tool Implementation
                    List<Presentation> presentations = presentationTools.getPresentations();

                    List<McpSchema.Content> contents = new ArrayList<>();
                    for(Presentation presentation : presentations) {
                        contents.add(new McpSchema.TextContent(presentation.toString()));
                    }
                    return new McpSchema.CallToolResult(contents, false);
                }
        );
        return syncToolSpecification;
    }
}