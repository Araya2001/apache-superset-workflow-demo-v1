// services/UploadService.ts
import axios from 'axios';

interface UploadCSVResponse {
    success: boolean;
    message: string;

    [key: string]: any;
}

class UploadService {
    private static instance: UploadService;

    private constructor() {
    }

    public static getInstance(): UploadService {
        if (!UploadService.instance) {
            UploadService.instance = new UploadService();
        }
        return UploadService.instance;
    }

    public async uploadCSV(file: File): Promise<UploadCSVResponse> {
        const formData = new FormData();
        formData.append('file', file);

        try {
            return await axios.post(
                'http://localhost:8081/upload-csv',
                formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                }
            );


        } catch (error) {
            console.error('Error uploading CSV:', error);
            return {
                success: false,
                message: 'An error occurred while uploading the file.',
                error,
            };
        }
    }
}

export default UploadService.getInstance();