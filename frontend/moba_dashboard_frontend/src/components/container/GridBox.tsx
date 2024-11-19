//import libraries
import type React from "react";

//import css file
import "../../style/components_style/container/container.css";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//define grid box component
const GridBox: React.FC<DefaultComponentProps> = (props: DefaultComponentProps) => {
	return (
		<div
			id={props.id}
			className={`grid-component-style-properties ${props.className}`}
			style={props.style}
		>
			{props.children}
		</div>
	);
};
//export grid box as default
export default GridBox;
