declare var paypal: any
declare module 'global' {
    // Place your global type declarations here
    interface Window {
        paypal: any;
    }
}

