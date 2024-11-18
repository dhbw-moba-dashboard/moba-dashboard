//import libraries
import type React from "react";

//import css file
import "../../style/components_style/container/container.css";

//define interface for grid box props
interface GridBoxProps {
	id?: string | undefined;
	className?: string | undefined;
	style?: React.CSSProperties;
	children?: React.ReactNode;
}

//define grid box component
const GridBox: React.FC<GridBoxProps> = (props: GridBoxProps) => {
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
