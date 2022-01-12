package com.kwiktwik.feedbackservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class GoogleFirebase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://Kwiktwik-internal.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("Exception in GoogleFirebase: " + e.getMessage());
        }
    }

    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("./kwiktwikfirebase.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://Kwiktwik-internal.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getUser(String uid) throws Exception {
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
// See the UserRecord reference doc for the contents of userRecord.
        System.out.println("Successfully fetched user data: " + userRecord.getEmail());
    }

    public String getUserEmail(String uid) {
        String email = "";
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
            email = userRecord.getEmail();
        }
        catch (Exception e){
            logger.info("getUserEmail: Exception while fetching userInfo: uid: {}, e: {}", uid, e.getMessage());
        }

        return email;
    }
}
