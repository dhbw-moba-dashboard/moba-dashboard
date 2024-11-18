//import libraries
import type React from "react";

//import css file
import "../../style/components_style/atoms/input.css";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//Import custom react components
import FlexBox from "../container/FlexBox";
import Text from "./texts";

//interface for select component props
interface SelectComponentProps extends DefaultComponentProps {
    selectComponentData?: any[] | undefined;
}

//define and export select component
export const Select: React.FC<SelectComponentProps> = (props: SelectComponentProps) => {
    return (
        <select id={props.id} style={props.style} className={props.className}>
            {
                // Iterate and return SelectOptionItem for each data value
                props.selectComponentData &&
                props.selectComponentData.map((currentDataValue) => (
                    <SelectOptionItem
                        key={currentDataValue['itemId']} // Add a unique key for list items
                        id={currentDataValue['itemId']}
                        selectOptionItemValue={currentDataValue['itemDataValue']}
                        selectOptionItemText={currentDataValue['itemText']}
                    />
                ))
            }
            {
                props.children
            }
        </select>
    );
};


//define interface select option item props
interface SelectOptionItemProps extends DefaultComponentProps {
    selectOptionItemValue?: string | undefined,
    selectOptionItemText?: string | undefined,
}

//crate and export select option item component
export const SelectOptionItem: React.FC<SelectOptionItemProps> = (props: SelectOptionItemProps) => {
    return (
        <option id={props.id} style={props.style} value={props.selectOptionItemValue} className={props.className}>{props.selectOptionItemText}</option>
    );
}

//define interface for check box component
interface CheckBoxProps extends DefaultComponentProps {
    checkBoxText?: string | undefined;
    checkBoxDefaultCheckState?: boolean | undefined;
    checkBoxAction?: (value?: any) => void;
}

//create and export check box component
export const CheckBox: React.FC<CheckBoxProps> = (props: CheckBoxProps) => {
    return (
        <FlexBox style={{alignItems: 'center', ...props.style}} className={props.className}>
            <input id={props.id} type="checkbox" onChange={props.checkBoxAction} checked={props.checkBoxDefaultCheckState}/>
            <Text style={{fontSize: '18px', marginLeft: '3%'}} textValue={props.checkBoxText!}/>
        </FlexBox>
    );
}