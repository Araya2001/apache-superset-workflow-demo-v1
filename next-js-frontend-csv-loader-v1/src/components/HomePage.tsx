// src/components/HomePage.tsx
"use client"; // Ensure this is a Client Component

import React, {useState} from 'react';
import FileInput from '../components/FileInput';
import StyledHeading from "@/components/StyledHeading";
import FileData from "@/components/FileData";
import JobRunInput from "@/components/JobRunInput";

const HomePage: React.FC = () => {
    const [file, setFile] = useState<File | null>(null);
    const [progress, setProgress] = useState<number>(0);


    const handleFileChange = (file: File | null) => {
        setFile(file);
    };

    return (
        <div className="flex flex-col justify-center ">
            <StyledHeading className={"text-slate-800"} text={"Data Warehouse File Loader"}></StyledHeading>
            <FileInput file={file} onFileChange={handleFileChange} uploadProgress={progress} onUploadProgress={setProgress}/>
            <FileData file={file} submissionProgress={progress}/>
            <StyledHeading className={"text-slate-800 pt-8"} text={"Manual Job Run Submission"}></StyledHeading>
            <JobRunInput/>
        </div>
    );
};

export default HomePage;