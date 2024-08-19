// src/components/FileInput.tsx
"use client";

import React, {useEffect, useState} from 'react';
import {Card} from "@nextui-org/card";
import {Divider} from "@nextui-org/divider";
import {Progress} from "@nextui-org/progress";

interface FileDataProps {
    file?: File | null;
    isFileSubmitted?: boolean;
    submissionProgress?: number;
    onSubmissionProgress?: (progress: number) => void;
    isSubmissionFinished?: boolean;

}

const FileData: React.FC<FileDataProps> = ({
                                               file = null,
                                               isFileSubmitted = true,
                                               submissionProgress = 0,
                                           }) => {
    const [selectedFile, setSelectedFile] = useState<File | null>(file);
    const pStyle: string = "font-light text-teal-100";

    useEffect(() => {
        if (file) {
            setSelectedFile(file);
        }
    }, [file]);


    return (
        <div className="flex flex-col justify-items-center pt-2">
            {file && (
                <Card className="flex flex-col gap-4 items-center justify-between p-4 bg-black/40 border-none" isBlurred>
                    <div>
                        <h2 className="text-3xl font-bold text-teal-100">
                            Review your file data
                        </h2>
                        <p className={pStyle}>File Name: {file.name}</p>
                        <p className={pStyle}>File Size: {(file.size / 1024).toFixed(2)} KB</p>
                    </div>
                    {isFileSubmitted && (
                        <div className="flex flex-col gap-4 items-center justify-between">
                            <Divider className="py-0.5" orientation="horizontal"/>
                            <div className="flex flex-col gap-4">
                                <h2 className="text-3xl font-bold text-teal-100">
                                    File upload progress
                                </h2>
                                <Progress color="secondary" aria-label="Loading..." value={submissionProgress}/>

                            </div>

                        </div>


                    )}
                </Card>
            )
            }
        </div>
    );
};

export default FileData;