export class ReminderHistory {
    patientName: string;
    day: string;
    expiredCount: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}