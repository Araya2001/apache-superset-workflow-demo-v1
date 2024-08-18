// src/components/FileInput.tsx
"use client";

import React, {ChangeEvent, useEffect, useState} from 'react';
import {Button, Input} from '@nextui-org/react';
import {Card} from "@nextui-org/card";

interface FileInputProps {
    file?: File | null;
    onFileChange?: (file: File | null) => void;
}

const FileInput: React.FC<FileInputProps> = ({file = null, onFileChange}) => {
    const [selectedFile, setSelectedFile] = useState<File | null>(file);

    useEffect(() => {
        if (file) {
            setSelectedFile(file);
        }
    }, [file]);

    const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
        let file: File | null = null;
        if (event.target.files) {
            file = event.target.files[0];
            setSelectedFile(file);
            onFileChange && onFileChange(file);
        }
    };

    const handleUpload = () => {
        if (selectedFile) {
            // Handle file upload logic here
            console.log('File selected:', selectedFile);
        }
    };

    return (
        <div className="flex flex-auto justify-center">
            <Card className="grid grid-cols-5 gap-4 justify-center p-5 bg-black/40 border-none" isBlurred>
                <Input
                    className="col-span-3"
                    type="file"
                    onChange={handleFileChange}
                    fullWidth
                    color={"default"}
                    radius={"sm"}
                />
                <Button
                    onClick={handleUpload}
                    disabled={!selectedFile}
                    color={"secondary"}
                    radius={"sm"}
                    className="col-span-2"
                >
                    Submit!
                </Button>

            </Card>

        </div>
    );
};

export default FileInput;