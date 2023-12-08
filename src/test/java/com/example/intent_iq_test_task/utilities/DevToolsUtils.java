package com.example.intent_iq_test_task.utilities;

import com.example.intent_iq_test_task.api.CheckoutRequestPartialUrls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.network.Network;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DevToolsUtils {

    private DevTools devTools;
    private final WebDriver driver;

    public DevToolsUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void startDevToolsSession() {
        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
    }

    public void stopDevToolsSession() {
        devTools.send(Network.disable());
    }

    public CompletableFuture<String> addRequestWillBeSentListener(CheckoutRequestPartialUrls requestUrlPart) {
        CompletableFuture<String> requestDataFuture = new CompletableFuture<>();
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String requestUrl = request.getRequest().getUrl();
            if (requestUrl.contains(requestUrlPart.getPartialUrl())) {
                if (request.getRequest().getPostData().isPresent()) {
                    requestDataFuture.complete(request.getRequest().getPostData().get());
                }
            }
        });
        return requestDataFuture;
    }

    public String getRequestBodyJsonFromListener(CompletableFuture<String> requestListener) {
        try {
            return requestListener.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
