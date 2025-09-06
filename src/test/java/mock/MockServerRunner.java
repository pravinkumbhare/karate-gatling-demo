package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class MockServerRunner {
    public static void main(String[] args) {
        WireMockServer server = new WireMockServer(wireMockConfig()
                                                    .port(8081)
                                                    .usingFilesUnderDirectory("src/test/resources")
//                                                    .usingFilesUnderDirectory("target/test-classes")
                                                    .extensions(new ResponseTemplateTransformer(true)) // <--- enable templating
        );
        server.start();

//        // Register stubs
//        server.stubFor(get(urlEqualTo("/hello"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Hello from WireMock!\"}")));
//
////        server.stubFor(post(urlEqualTo("/user"))
////                .willReturn(aResponse()
////                        .withStatus(201)
////                        .withHeader("Content-Type", "application/json")
////                        .withBody("{\"status\": \"User created successfully\"}")));
//
//        server.stubFor(get(urlEqualTo("/user"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"status\": \"Use POST to create a user\"}")));
//
//        server.stubFor(get(urlEqualTo("/pravin"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Hello Pravin this is mock API Response!\"}")));

        System.out.println("WireMock server started at http://localhost:8081");
    }
}
