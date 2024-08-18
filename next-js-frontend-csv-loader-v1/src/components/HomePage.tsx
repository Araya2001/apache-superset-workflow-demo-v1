// src/components/HomePage.tsx
"use client"; // Ensure this is a Client Component

import React, {useState} from 'react';
import FileInput from '../components/FileInput';
import StyledHeading from "@/components/StyledHeading";
import FileData from "@/components/FileData";

const HomePage: React.FC = () => {
    const [file, setFile] = useState<File | null>(null);

    const handleFileChange = (file: File | null) => {
        setFile(file);
    };

    return (
        <div className="flex flex-col justify-center ">
            <StyledHeading className={"text-slate-800"} text={"Data Warehouse File Loader"}></StyledHeading>

            <FileInput file={file} onFileChange={handleFileChange}/>
            <FileData file={file}/>
        </div>
    );
};

export default HomePage;