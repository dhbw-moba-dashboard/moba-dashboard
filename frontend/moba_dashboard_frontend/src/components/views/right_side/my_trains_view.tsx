//import react library
import React, {useEffect, useState} from "react";

//import custom react components
import {ContentContainer} from "../../container/content_container";
import GridBox from "../../container/GridBox";
import {TrainOptionElement} from "../../molecules/train_option_element";
import Text, {ImageText} from "../../atoms/texts";

//import custom ts functions
import {setConsoleMessage} from "../../../logic/tools/messages";
import FlexBox from "../../container/FlexBox";

//create and export default my trains container
export default function MyTrainsContainer() {
    //define state hook to load train options
    const [trainOptions, setTrainOptions] = useState<any[]>([]);

    //use effect to load train options from .json file
    useEffect(() => {
        //fetch data from .json file
        fetch('../data/trains.json')
            .then(jsonResponse => jsonResponse.json())
            .then(jsonData => setTrainOptions(jsonData))
            .catch(readJsonFileError => setConsoleMessage(readJsonFileError, true));
    }, []);

    //define map for different train information options
    const trainInformationOptions = new Map<string, [string, string]>([
        ["currentSpeed", [`Aktuelle Geschwindigkeit: km/h`, "Icon_Speedometer_IOS_White"]],
        ["coalValue", [`Aktueller Kohle stand::  kg`, "Icon_Coal_IOS_White"]],
        ["waterValue", [`Aktueller Wasser Stand: l`, "Icon_Water_IOS_White"]],
        ["sandValue", [`Aktueller Sand stand: kg`, "Icon_Sand_IOS_White"]],
    ]);

    //return created ui component
    return (
        <>
            <ContentContainer contentContainerHeaderText="Meine Züge">
                <GridBox>
                    {
                        //check if to set train options
                        trainOptions
                            ? trainOptions.map((currentTrain: any) => (
                                <TrainOptionElement
                                    key={currentTrain['trainID']}
                                    trainOptionElementText={currentTrain['trainName']}
                                    trainOptionElementImage={currentTrain['trainImage']}
                                />
                            ))
                            : <ImageText textValue="No trains found!"/>
                    }
                </GridBox>
                <hr/>
                <div>
                    {
                        //load train information data
                        Array.from(trainInformationOptions).map(([key, currentTrainInformation]) => (
                            <ImageText key={key} textStyle={{fontSize: '18px'}} textValue={currentTrainInformation[0]}
                                       imageStyle={{height: 'auto', width: '24px'}} textImage={`images/general/${currentTrainInformation[1]}.png`}/>
                        ))
                    }
                </div>
                <hr/>
                <Text style={{fontWeight: 'bold', fontSize: '20px'}} textValue="trAIn - Das Modellbahn AI System"/>
                <FlexBox>

                </FlexBox>
            </ContentContainer>
        </>
    );
}