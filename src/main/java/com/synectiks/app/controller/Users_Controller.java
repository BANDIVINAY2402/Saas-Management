package com.synectiks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.app.entity.UsersEntity;
import com.synectiks.app.repository.UserEntityRepository;
import com.synectiks.app.service.UsersEntityService;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

@RestController
//@Slf4j
public class Users_Controller {

    private static final String EMAIL_PROTOCOL = "email";

    @Value("${aws.sns.topicArn}")
    private String topicArn;
    
   String tarn="arn:aws:sns:us-east-1:657907747545:SaasManagement";
    @Autowired
    private SnsClient snsClient;

    @Autowired
    private UsersEntityService usersEntityService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUsers(@RequestBody UsersEntity usersEntity) {
        try {
            String response = usersEntityService.createuser(usersEntity);

            if ("user registered successfully".equals(response)) {
      
              System.out.println("User registration successful for email: {}"+ usersEntity.getEmail());

                // Subscribe user to the SNS topic
                addSubscription(usersEntity);

                // Send credentials email
                String emailMessage = "Your account has been created successfully. Your credentials are:\n"
                        + "Username: " + usersEntity.getUsername() + "\n"
                        + "Password: " + usersEntity.getPassword();

//                sendMailWithCredentials(emailMessage, usersEntity.getEmail());
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
        	   System.out.println("An error occurred while creating the user: "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    private void addSubscription(UsersEntity usersEntity) {
        try {
            SubscribeRequest subscribeRequest = SubscribeRequest.builder()
                    .protocol(EMAIL_PROTOCOL)
                    .endpoint(usersEntity.getEmail())
                    .topicArn(tarn)
                    .build();

            SubscribeResponse subscribeResponse = snsClient.subscribe(subscribeRequest);
            System.out.println("Subscription ARN: {}"+ subscribeResponse.subscriptionArn());
        } catch (Exception e) {
        	  System.out.println("Failed to subscribe email {} to topic {}"+ usersEntity.getEmail()+ topicArn+ e);
        }
    }

    private void sendMailWithCredentials(String emailMessage, String email) {
        try {
            PublishRequest publishRequest = PublishRequest.builder()
                    .topicArn(tarn)
                    .message(emailMessage)
                    .subject("Welcome to Synectiks")
                    .build();

            snsClient.publish(publishRequest);
            System.out.println("Credentials email sent to: {}"+ email);
        } catch (Exception e) {
        	  System.out.println("Failed to send email to {}: "+ email+ e);
        }
    }
}
