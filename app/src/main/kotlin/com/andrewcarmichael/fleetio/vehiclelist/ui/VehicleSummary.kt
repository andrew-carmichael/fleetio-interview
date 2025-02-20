package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andrewcarmichael.fleetio.R.drawable
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.FakeVehicleData
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.Tag
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel

@Composable
fun VehicleSummary(
    onPressed: () -> Unit,
    vehicleModel: VehicleModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(
                enabled = true,
                onClick = onPressed
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .heightIn(min = 64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = vehicleModel.imageUrl,
                placeholder = painterResource(drawable.local_shipping),
                fallback = painterResource(drawable.local_shipping),
                contentDescription = vehicleModel.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = vehicleModel.name, style = MaterialTheme.typography.titleSmall)
                vehicleModel.description?.let {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    vehicleModel.tags.filterNot {
                        it is Tag.VehicleStatus && it == Tag.VehicleStatus.Unknown || it is Tag.VehicleType && it == Tag.VehicleType.Unknown
                    }.forEach { tag ->
                        VehicleInfoChip(tag)
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleInfoChip(
    tag: Tag,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 1.dp,
        modifier = modifier
    ) {
        Text(
            text = stringResource(tag.displayStringRes),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewVehicleSummary() {
//    FleetioTheme {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(items = FakeVehicleData.vehicles) { vehicle ->
//                VehicleSummary(
//                    onPressed = {},
//                    vehicleTitle = vehicle.name,
//                    vehicleSubtitle = vehicle.description,
//                    imageModel = null,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//    }
//}
