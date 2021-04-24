interface Scripts {
    name: string;
    src: string;
}  
export const ScriptStore: Scripts[] = [
    {name: 'cardhash', src: 'https://sandbox.boletobancario.com/boletofacil/wro/direct-checkout.min.js'},
    {name: 'cardhashcode', src: '../assets/cardhash.js'}
];