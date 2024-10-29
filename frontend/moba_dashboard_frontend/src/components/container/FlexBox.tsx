//import libraries
import React from "react";

//import css file
import '../../style/components_style/container.css';

//define interface for FlexBox
interface FlexBoxProps {
    id?: string | undefined,
    style?: React.CSSProperties,
    className?: string | undefined,
    children?: React.ReactNode
}

//create default flexbox component
const FlexBox: React.FC<FlexBoxProps> = (props: FlexBoxProps) => {
    return (
        <div id={props.id} className={`flexbox-style-properties ${props.className}`} style={props.style}>{props.children}</div>
    );
}
//define default FlexBox
export default FlexBox;


