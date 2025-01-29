//import default react library
import React from "react";

import { Select } from "../../../atoms/input";
//import custom react component
import { ContentContainer } from "../../../container/content_container";

//import chart component
import { ChartComponent } from "../../../charts/Graph";

//create and export default train data container
export default function TrainDataContainer() {
	//return created ui component
	return (
		<>
			<ContentContainer
				contentContainerHeaderText="Zug Daten"
				topHeaderSectionChildren={<TrainDataTypeSelect />}
			></ContentContainer>
		</>
	);
}

//create train data type select component
function TrainDataTypeSelect() {
	return (
		<Select style={{ fontWeight: "bold", fontSize: "18px" }}>
			<option>Ø Geschwindigkeit</option>
			<optgroup label="Ø Verbrauch">
				<option>Wasser</option>
				<option>Kohle</option>
				<option>Sand</option>
			</optgroup>
		</Select>
	);
}
