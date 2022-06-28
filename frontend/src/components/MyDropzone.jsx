import React, { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';

export function MyDropzone() {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the files here ...</p>
      ) : (
        <p>Drag 'n' drop your photo here, or click to select a file</p>
      )}
    </div>
  );
}
