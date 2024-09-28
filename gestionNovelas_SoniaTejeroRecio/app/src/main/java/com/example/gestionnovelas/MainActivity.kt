//package com.example.gestionnovelas.ui.theme
//CON LA BASE DE DATOS....................................//
/*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.gestionnovelas.R
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreen()
        }
    }
}

@Composable
fun SecondScreen() {
    val backgroundImage = painterResource(id = R.drawable.fondo_segunda_pantalla)
    var novelTitle by remember { mutableStateOf(TextFieldValue("")) }
    val novels = remember { mutableStateListOf<Novel>() }
    var showMenu by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val db = remember { DatabaseModule.provideDatabase(context) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showMenu = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Agregar Novelas")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    showAddDialog = true
                    showMenu = false
                }) {
                    Text("Agregar Novela")
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    (context as ComponentActivity).lifecycleScope.launch {
                        novels.clear()
                        novels.addAll(db.novelDao().getAllNovels())
                    }
                }) {
                    Text("Mostrar Novelas")
                }
            }
            if (showAddDialog) {
                Dialog(
                    onDismissRequest = { showAddDialog = false },
                    properties = DialogProperties(dismissOnClickOutside = false)
                ) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 8.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = novelTitle,
                                onValueChange = { novelTitle = it },
                                label = { Text("Título de la novela") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                            Button(
                                onClick = {
                                    if (novelTitle.text.isNotBlank()) {
                                        (context as ComponentActivity).lifecycleScope.launch {
                                            db.novelDao().insert(Novel(title = novelTitle.text))
                                            novelTitle = TextFieldValue("")
                                            showAddDialog = false
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text("Guardar Novela")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            novels.forEach { novel ->
                Text(text = novel.title, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

 */

package com.example.gestionnovelas.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.gestionnovelas.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionNovelasTheme {
                SecondScreen()
            }
        }
    }
}

@Composable
fun SecondScreen() {
    val backgroundImage = painterResource(id = R.drawable.fondo_segunda_pantalla)
    var novelTitle by remember { mutableStateOf(TextFieldValue("")) }
    val novels = remember { mutableStateListOf<String>() }
    var showAddDialog by remember { mutableStateOf(false) }
    var showNovelsDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showAddDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Agregar Novela")
            }
            Button(
                onClick = { showNovelsDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Mostrar Novelas")
            }
            if (showAddDialog) {
                AddNovelDialog(
                    novelTitle = novelTitle,
                    onNovelTitleChange = { novelTitle = it },
                    onSaveClick = {
                        if (novelTitle.text.isNotBlank()) {
                            novels.add(novelTitle.text)
                            novelTitle = TextFieldValue("")
                            showAddDialog = false
                        }
                    },
                    onDismissRequest = { showAddDialog = false }
                )
            }
            if (showNovelsDialog) {
                ShowNovelsDialog(
                    novels = novels,
                    onDismissRequest = { showNovelsDialog = false }
                )
            }
        }
    }
}

@Composable
fun AddNovelDialog(
    novelTitle: TextFieldValue,
    onNovelTitleChange: (TextFieldValue) -> Unit,
    onSaveClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = novelTitle,
                    onValueChange = onNovelTitleChange,
                    label = { Text("Título de la novela") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Guardar Novela")
                }
            }
        }
    }
}

@Composable
fun ShowNovelsDialog(
    novels: List<String>,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text("Cerrar")
            }
        },
        text = {
            Column {
                novels.forEach { novel ->
                    Text(text = novel, modifier = Modifier.padding(8.dp))
                }
            }
        }
    )
}