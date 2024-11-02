//import libraries
import React from "react";

//import css file
import '../../style/components_style/container/container.css';
import {HeaderText} from "../atoms/texts";


//define interface for content container properties
interface ContentContainerProperties {
    contentContainerID?: string | undefined,
    contentContainerHeaderText: string,
    className?: string | undefined,
    style?: React.CSSProperties,
    children?: React.ReactNode
}

//create and export content container component
export const ContentContainer: React.FC<ContentContainerProperties> = (props: ContentContainerProperties) => {
    return (
        <div id={props.contentContainerID} className={`content-container-style-properties ${props.className}`} style={props.style}>
            <HeaderText textValue={props.contentContainerHeaderText}/>
            {props.children}
        </div>
    );
};