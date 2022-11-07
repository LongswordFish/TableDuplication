import { Table } from "react-bootstrap";

const RealEstateTable=({realEstates,tableName})=>{

    return (
        <div className="realEstates-table">
            <h3 style={{"textAlign":"center"}}>{tableName.toUpperCase()}</h3>
            {realEstates&&realEstates.length!==0 ? (
          <div className="realEstates-container">

            <Table striped bordered hover size="sm">
              <thead>
                <tr>
                  <th>Address</th>
                  <th>City</th>
                  <th>State</th>
                </tr>
              </thead>
              <tbody>
                    {realEstates.map((realEstate) => (
                    <tr key={realEstate?.address} >
                        <td>{realEstate?.address}</td>
                        <td>{realEstate?.city}</td>
                        <td>{realEstate?.state}</td>                       
                   </tr>
                    ))}
              </tbody>
            </Table>
            
          </div>
        ) : (
          <div style={{"marginBottom":"20px","textAlign":"center"}}>No Real Estates Found!</div>
        )}
        </div>
    )
}

export default RealEstateTable;