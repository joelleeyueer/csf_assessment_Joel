export interface Details {
    name: string;
    title: string;
    comments: string;
    archive: File;
}

export interface Bundle {
    bundleId: string;
    date: string;
    title: string;
    name: string;
    comments: string;
    urls: string[];
}

export interface PhotoMain {
    title: string;
    date: string;
}
  