import { Link } from "react-router-dom";

const UnauthorizedPage = () => {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4">
            <div className="w-full max-w-md p-8 space-y-4 bg-white rounded-lg shadow-md">
                <h1 className="text-2xl font-bold text-center text-red-600">Unauthorized Access</h1>
                <p className="text-center text-gray-700">
                    You don't have permission to access this page.
                </p>
                <div className="flex justify-center mt-6">
                    <Link
                        to="/"
                        className="px-4 py-2 text-white bg-blue-600 rounded hover:bg-blue-700 focus:outline-none"
                    >
                        Go back to Home
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default UnauthorizedPage;
