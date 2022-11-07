// src/utils/api.js
const apiUrl = process.env.REACT_APP_API_URL;

//used to get real estates from table_a
//no auth needed
export async function retrieveTableA() {
    console.log('Requesting table_a data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/A`, {
        method: "GET"});
      if (!res.ok) {
        if(res.status>=400 && res.status<500){
            throw new Error(`Bad Request!`);
        }
        if(res.status>=500){
            throw new Error(`Server error, please try again later!`);
        }    
      }
      const data = await res.json();
      return data;
    } catch (err) {
      console.error('Unable to call GET /api/real-estate/A', { err });
    }
}

//used to get real estates from table_b
//use path to deduplicate table_b
export async function retrieveTableB() {
    console.log('Requesting table_b data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/B`, {
        method: "GET"});
      if (!res.ok) {
        if(res.status>=400 && res.status<500){
            throw new Error(`Bad Request!`);
        }
        if(res.status>=500){
            throw new Error(`Server error, please try again later!`);
        }    
      }
      const data = await res.json();
      return data;
    } catch (err) {
      console.error('Unable to call GET /api/real-estate/A', { err });
    }
}

//deduplicate table b method 2
//use request body to deduplicate table_b
export async function deduplicateTableBWithRequestBody(tables) {
    console.log('Deduplicating table_b data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/remove-duplicates/`, {
        headers: {
            'Content-Type': "application/json",
          },method: "Post",body:JSON.stringify(tables)});
      if (!res.ok) {
        if(res.status>=400 && res.status<500){
            throw new Error(`Bad Request!`);
        }
        if(res.status>=500){
            throw new Error(`Server error, please try again later!`);
        }    
      }
      const data = await res.json();
      return data;
    } 
    catch (err) {
      console.error('Unable to call POST api/real-estate/remove-duplicates/', { err });
      throw err;
    }
    
}

  //deduplicate table b method 1
export async function deduplicateTableBWithPath() {
    console.log('Deduplicating table_b data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/remove-duplicates/table_b/table_a`, {
        method: "Post"});
      if (!res.ok) {
        if(res.status>=400 && res.status<500){
            throw new Error(`Bad Request!`);
        }
        if(res.status>=500){
            throw new Error(`Server error, please try again later!`);
        }    
      }
      const data = await res.json();
      return data;
    } catch (err) {
      console.error('Unable to call GET /api/real-estate/A', { err });
    }
}

  