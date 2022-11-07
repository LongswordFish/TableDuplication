import RealEstateTable from '../real-estate-table/RealEstateTable.component';
import {Container,Row,Col,Button} from 'react-bootstrap';
import { useState,useEffect } from 'react';

import { retrieveTableA,retrieveTableB,deduplicateTableBWithPath,deduplicateTableBWithRequestBody } from '../../utils/api';
import BeatLoader  from "react-spinners/BeatLoader";
import { toast } from 'react-toastify';

//used to compare and deduplicate tables
const RealEstateCompare=()=>{

    const [tableA,setTableA] = useState([]);
    const [tableB,setTableB] = useState([]);
    const[loading,setLoading] = useState(true);

    //table name
    const tableNameA="table_a";
    const tableNameB="table_b";

    //render table_a and table_b when the page begins
    useEffect(()=>{
        const getTableA = async ()=>{
          try{
              const tableA = await retrieveTableA();
              setTableA(tableA);
          }catch(err){
            console.log(err);
          }
        }
        const getTableB = async ()=>{
            try{
                const tableB = await retrieveTableB();
                setTableB(tableB);
            }catch(err){
              console.log(err);
            }
          }
        setLoading(true);       
        getTableA();
        getTableB();
        setLoading(false);   
    
    },[]);
    
    //deduplicate table B using path method
    const handleDuplicateTableB = async()=>{
        try{
            setLoading(true);  
            const duplicatedTableB = await deduplicateTableBWithPath();          
            setTableB(duplicatedTableB);
            toast.success("Deduplicate table B success!",{
                position: toast.POSITION.BOTTOM_RIGHT
            });
            setLoading(false);  
        }catch(err){
            setLoading(false);
            toast.error(err?.message, {
                position: toast.POSITION.BOTTOM_RIGHT
            });
      }
    }
    
    //deduplicate table B using request body method
    const handleDuplicateTableB2 = async()=>{
        try{
            const tables = {
                "tableA":tableA,
                "tableB":tableB
            }
            setLoading(true);
            const duplicatedTableB = await deduplicateTableBWithRequestBody(tables);       
            setTableB(duplicatedTableB);

            toast.success("Deduplicate table B success!", {
                position: toast.POSITION.BOTTOM_RIGHT
            });

            setLoading(false);  
        }catch(err){
            setLoading(false);
            toast.error(err?.message, {
                position: toast.POSITION.BOTTOM_RIGHT
            });
        }
    }


    return (<Container className="content">
        <Row>
        <Col sm={6}>
            <RealEstateTable realEstates={tableA} tableName={tableNameA}/>
        </Col>
        <Col sm={6}>
        <RealEstateTable realEstates={tableB} tableName={tableNameB}/>
        </Col>
        {
            loading?
            <div style={{ margin:"20px auto", width:"200px"}}>
                <BeatLoader 
                    color="#36d7b7"
                    loading={loading}
                    size={15}
                    aria-label="Loading Spinner"
                    data-testid="loader"
                    margin="10px"
                />
            </div>              
            : <Button style={{ margin:"20px auto", width:"200px"}} variant="success" onClick={handleDuplicateTableB2} >Deduplicate Table B</Button>  
        }
        </Row>

    </Container>
    )
}

export default RealEstateCompare;