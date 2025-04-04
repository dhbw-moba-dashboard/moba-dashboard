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
import Image from "./components/atoms/images";

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
					style={{alignItems: "center"}}
				>
					<Image style={{height: '60px', width: 'auto', borderRadius: '25px'}} imageValue="images/logo/Train_Logo.jpg"/>
					<HeaderText style={{fontSize: '48px', color: '#3fbbd7'}} textValue="we.love.trains" />
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
