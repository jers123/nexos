import { HttpHeaders } from "@angular/common/http";

export const environment = {
    name: 'Parameters',
    httpOptions: {
        headers: new HttpHeaders(
            {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa(`jers:parameters`),
                'Accept': 'application/json'
            }
        )
    },
    paths: {
        globalPath: 'http://localhost:8080/inventory',
        subPath: {
            position: '/position',
            product: '/product',
            user: '/user'
        },
        functionPath: {
            createPath: '/create',
            deletePath: '/delete/',
            getAllPath: '/get-all',
            getIdPath: '/get-id/',
            updatePath: '/update'
        }
    },
    msg: {
        connectionAPI: 'No hayconexion con la API'
    }
};