package com.eliascardona.ecommerce.components.features.shopping_cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartPersistenceViewModel

@Composable
fun TeacherForm(
    viewModel: ShoppingCartPersistenceViewModel,
    modifier: Modifier = Modifier
) {
    val teacherNameFlow = viewModel.teacherNameFlow

    val teacherName by teacherNameFlow.collectAsState(initial = "")

    var teacherNameInput by remember(teacherName) { mutableStateOf(teacherName) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Save a teacher",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = teacherNameInput,
            onValueChange = { teacherNameInput = it },
            label = { Text("Name") },
            placeholder = { Text("Write the teacher's name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.saveTeacher(
                    teacherNameInput
                )
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Save teacher")
        }
    }
}