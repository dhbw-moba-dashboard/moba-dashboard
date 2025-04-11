//import external created functions
import {setConsoleMessage} from "./messages";

//function to search user
export default function searchData(searchTerm: string, passedDataObject: [] | undefined): any | [] {
    //variable to hold search results
    let searchBasedResult: any[] = [];

    //try to use search for value
    try {
        //check if user object is not empty
        if (passedDataObject) {
            //filter options based on search term if it's provided, otherwise, display all options
            const filteredOptions = searchTerm ?
                passedDataObject.filter(option =>
                    Object.values(option).some(value =>
                        typeof value === 'string' && value.toLowerCase().includes(searchTerm.toLowerCase())
                    )
                )
                :
                passedDataObject;

            //update state with filtered options
            searchBasedResult = filteredOptions;
        }
    } catch (searchValueError: any) {
        //call function to set error message
        setConsoleMessage(searchValueError, true);
    }
    //return search results
    return searchBasedResult;
}