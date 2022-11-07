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
        throw new Error(`${res.status} ${res.statusText}`);
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
    console.log('Requesting table_a data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/B`, {
        method: "GET"});
      if (!res.ok) {
        throw new Error(`${res.status} ${res.statusText}`);
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
        throw new Error(`${res.status} ${res.statusText}`);
      }
      const data = await res.json();
      return data;
    } catch (err) {
      console.error('Unable to call GET /api/real-estate/A', { err });
    }
}

  //deduplicate table b method 1
export async function deduplicateTableBWithPath() {
    console.log('Deduplicating table_b data... ');
    try {
      const res = await fetch(`${apiUrl}/api/real-estate/remove-duplicates/table_b/table_a`, {
        method: "Post"});
      if (!res.ok) {
        throw new Error(`${res.status} ${res.statusText}`);
      }
      const data = await res.json();
      return data;
    } catch (err) {
      console.error('Unable to call GET /api/real-estate/A', { err });
    }
}

  