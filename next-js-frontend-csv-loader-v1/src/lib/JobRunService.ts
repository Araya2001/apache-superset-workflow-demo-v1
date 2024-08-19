// services/UploadService.ts
import axios from 'axios';

interface UploadCSVResponse {
    success: boolean;
    message: string;

    [key: string]: any;
}

const API_URL: string = 'http://localhost:8082/etl/api/v1';

class JobRunService {

    private static instance: JobRunService;

    private constructor() {
    }

    public static getInstance(): JobRunService {
        if (!JobRunService.instance) {
            JobRunService.instance = new JobRunService();
        }
        return JobRunService.instance;
    }

    public async runJob(): Promise<void> {
        try {
            const response = await axios.get(`${API_URL}/run-job`);
            return response.data;
        } catch (error) {
            console.error('Error running the job:', error);
            throw error;
        }
    }
}

export default JobRunService.getInstance();