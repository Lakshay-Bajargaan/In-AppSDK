package com.example.mobileapp1

//import com.salesforce.android.smi.ui.PreChatField as UiPreChatField
//import com.salesforce.android.smi.ui.PreChatFieldValueProvider

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.forEach
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.salesforce.android.smi.core.CoreConfiguration
//import com.salesforce.android.smi.core.PreChatClient
import com.salesforce.android.smi.ui.UIClient
import com.salesforce.android.smi.ui.UIConfiguration
import java.util.UUID


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupportScreen()
        }
    }
}

@Composable
fun SupportScreen() {
    val context = LocalContext.current
    // We use a coroutine scope if we need async work, but launch is synchronous here
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Need Help?", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    try {
                        // Step 1: Load the core configuration
                        val coreConfig = CoreConfiguration.fromFile(context, "configFile.json")

                        // Step 2: Create a User ID
                        val conversationID = UUID.randomUUID()

                        // Step 3: Create the PreChatClient object with your data
                      /*  val preChatClient = PreChatClient { fields ->
                            // The `fields` variable is a list of PreChatField objects
                            fields.forEach { field ->
                                when (field.name) {
                                    "_firstName" -> {
                                        field.userInput = "Jane"
                                        field.isEditable = false
                                    }
                                    "_lastName" -> {
                                        field.userInput = "Doe"
                                        field.isEditable = false
                                    }
                                    "_email" -> {
                                        field.userInput = "jane.doe@example.com"
                                    }
                                }
                            }
                            // Return the modified list
                            fields
                        } // <-- The extra closing brace was here

                        // Step 4: Create the UI Configuration, passing the preChatClient into the constructor
                        val uiConfig = UIConfiguration(coreConfig, conversationID, preChatClient)
*/
                        val uiConfig = UIConfiguration(coreConfig, conversationID)
                        // Step 5: Create the UI Client from the configuration
                        val uiClient = UIClient.Factory.create(uiConfig)

                        // Step 6: Launch the Activity
                        uiClient.openConversationActivity(context)

                    } catch (e: Exception) {
                        // If anything goes wrong, this will catch the error
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            ) {
                Text(text = "Chat with Support")
            }
        }
    }
}
