//import app styling
import "./App.css";

import { createContext, useState } from "react";

//import custom react components
import { ImageButton } from "./components/atoms/buttons";
import { HeaderText } from "./components/atoms/texts";
import FlexBox from "./components/container/FlexBox";

import MainLeftSideContainer from "./components/views/left_side/main_left_side_container";

//import container view component
import MyTrainsContainer from "./components/views/right_side/my_trains_view";

//create and export data transfer context
export const DataTransferContext = createContext<any | null>(null);

function App() {
	//define state hook for selected train
	const [selectedTrain, setSelectedTrain] = useState<string>();

	//data transfer object
	const dataTransferObject: object = {
		selectedTrain: selectedTrain,
		setSelectedTrain: setSelectedTrain,
	};

	//return created ui components
	return (
		<DataTransferContext.Provider value={dataTransferObject}>
			<div className="App">
				<FlexBox
					style={{ justifyContent: "space-between", alignItems: "center" }}
				>
					<HeaderText textValue="we.love.trains" />
				</FlexBox>
				<FlexBox
					style={{ justifyContent: "space-between", alignItems: "flex-start" }}
				>
					<div style={{ width: "40%" }}>
						<MainLeftSideContainer />
					</div>
					<div style={{ width: "59%" }}>
						<MyTrainsContainer />
					</div>
				</FlexBox>
			</div>
		</DataTransferContext.Provider>
	);
}

export default App;
