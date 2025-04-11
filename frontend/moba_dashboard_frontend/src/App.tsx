//import app styling
import "./App.css";

import {createContext, useEffect, useState} from "react";

//import custom react components
import { ImageButton } from "./components/atoms/buttons";
import { HeaderText } from "./components/atoms/texts";
import FlexBox from "./components/container/FlexBox";

import MainLeftSideContainer from "./components/views/left_side/main_left_side_container";
import Image from "./components/atoms/images";

//import container view component
import MyTrainsContainer from "./components/views/right_side/my_trains_view";

//import custom ts function
import {setConsoleMessage} from "./logic/tools/messages";

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

	//define function to set background initial
	function applyInitialBackground() {
		const darkBackground = localStorage.getItem('darkBackground');

		const isDark = darkBackground !== 'false'; // default to dark if not set

		document.body.classList.remove("dark-mode-background", "light-mode-background");
		document.body.classList.add(isDark ? "dark-mode-background" : "light-mode-background");
	}

	//use efect to apply initial background color
	useEffect(() => {
		applyInitialBackground();
	}, []);

	//define function to change application background color
	function changeApplicationBackgroundColor(): void {
		//try catch for error handling
		try {
			//get current stored background state
			const darkBackground = localStorage.getItem('darkBackground');

			//check if not exists
			if (!darkBackground) {
				//create dark background in local storage
				localStorage.setItem('darkBackground', 'true');
				return;
			}

			//set new state because clicked
			localStorage.setItem('darkBackground', (darkBackground === 'false') ? 'true' : 'false');

			//set background to application
			document.body.classList.remove("dark-mode-background", "light-mode-background");
			document.body.classList.add((darkBackground === 'false') ? 'dark-mode-background' : 'light-mode-background');
		}
		catch (changeApplicationBackgroundError: any) {
			//set error message
			setConsoleMessage(changeApplicationBackgroundError.message, true);
	}

	//return created ui components
	return (
		<DataTransferContext.Provider value={dataTransferObject}>
			<div className="App">
				<FlexBox style={{justifyContent: 'space-between', alignItems: "center"}}>
					<FlexBox style={{alignItems: "center"}}>
						<Image style={{height: '60px', width: 'auto', borderRadius: '25px'}} imageValue="images/logo/Train_Logo.jpg"/>
						<HeaderText style={{fontSize: '48px', color: '#3fbbd7'}} textValue="we.love.trains" />
					</FlexBox>
					<ImageButton style={{marginRight: '4%'}} imageStyle={{height: '36px', width: 'auto'}} buttonImage={`images/general/Icon_Day_Night_IOS_White.png`}
								 buttonAction={changeApplicationBackgroundColor}/>
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
