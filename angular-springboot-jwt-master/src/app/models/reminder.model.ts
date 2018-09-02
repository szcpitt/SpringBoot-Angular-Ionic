export class Reminder {
    patientId: string;
    patientName: string;
    highCount: string;
    midCount: string;
    lowCount: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}