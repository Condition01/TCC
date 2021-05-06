interface Scripts {
    name: string;
    src: string;
}  
export const ScriptStore: Scripts[] = [
    {name: 'cardhashcode', src: 'https://sandbox.boletobancario.com/boletofacil/wro/direct-checkout.min.js'},
    {name: 'cardhash', src: '../assets/cardhash.js'}
];