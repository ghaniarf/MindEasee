package org.d3if0041.mopro1.proyekcoba.ui.screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0041.mopro1.proyekcoba.R
import org.d3if0041.mopro1.proyekcoba.ui.theme.ProyekCobaTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreen(navController: NavHostController) {
    var oldPassword by remember { mutableStateOf("") }
    var showOldPassword by remember { mutableStateOf(false) }  // State untuk toggle visibility password lama
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }  // State untuk toggle visibility password baru
    var confirmPassword by remember { mutableStateOf("") }
    val oldPasswordFocusRequest = remember { FocusRequester() }
    val passwordFocusRequest = remember { FocusRequester() }
    val confirmPasswordFocusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val textFieldShape = RoundedCornerShape(12.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.back),
                                contentDescription = "Back",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.password),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(330.dp)
                        .border(0.5.dp, Color.Gray, textFieldShape)
                        .background(Color.White, textFieldShape)
                        .padding(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Password Lama
                        OutlinedTextField(
                            value = oldPassword,
                            onValueChange = { oldPassword = it },
                            label = { Text("Password Lama") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = textFieldShape,
                            visualTransformation = if (showOldPassword) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (showOldPassword) R.drawable.eye else R.drawable.eye_hide
                                IconButton(onClick = { showOldPassword = !showOldPassword }) {
                                    Icon(
                                        painter = painterResource(id = image),
                                        contentDescription = "Toggle Password Visibility"
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = { passwordFocusRequest.requestFocus() })
                        )

                       //Password Baru
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password Baru") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = textFieldShape,
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (showPassword) R.drawable.eye else R.drawable.eye_hide
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(
                                        painter = painterResource(id = image),
                                        contentDescription = "Toggle Password Visibility"
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = { confirmPasswordFocusRequest.requestFocus() })
                        )

                        //Konfirmasi Password Baru
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text("Konfirmasi Password ") },
                            modifier = Modifier.fillMaxWidth().focusRequester(confirmPasswordFocusRequest),
                            shape = textFieldShape,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                if (isValidCredentials(oldPassword, password, confirmPassword)) {
                                    navController.navigate("lain")
                                } else {
                                    Toast.makeText(context, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                                }
                                keyboardController?.hide()
                            })
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        // Tombol "Ubah"
                        Button(
                            onClick = {
                                if (isValidCredentials(oldPassword, password, confirmPassword)) {
                                    navController.navigate("lain")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Password tidak sesuai",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                "Ubah",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}

private fun isValidCredentials(oldPassword: String, newPassword: String, confirmPassword: String): Boolean {
    return oldPassword.isNotEmpty() && newPassword.isNotEmpty() && newPassword == confirmPassword
}

@Composable
@Preview(showBackground = true)
fun PasswordScreenPreview() {
    ProyekCobaTheme {
        PasswordScreen(rememberNavController())
    }
}
