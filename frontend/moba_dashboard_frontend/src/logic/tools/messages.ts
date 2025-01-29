//create and export function to set messages to console
export const setConsoleMessage = (passedMessage: string, errorMessage: boolean = false, expandedMessageData: boolean = false): void => {
    //check if expanded user text has to be created
    let expandedDataText: string = "";
    if(expandedMessageData){
        //created expanded user message
        expandedDataText = `- Date_Information: ${(new Date()).toString()}\n- Text_Character_Length: ${passedMessage.toString().length}\n- Error_Message: ${errorMessage.toString()}`;
    }

    //check which console log to set (default/ error)
    (!errorMessage) ? console.log(`${passedMessage}\n${expandedMessageData}`): console.error(`${passedMessage}\n${expandedDataText}`);
}